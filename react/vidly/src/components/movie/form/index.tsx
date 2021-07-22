import React, {ChangeEvent, useMemo, useState} from "react";
import {getMovie, persist} from "../../../services/fakeMovieService";
import Movie from "../../../domain/movie";
import Genre from "../../../domain/genre";
import {getGenre, getGenres} from "../../../services/fakeGenreService";
import ValidationReport from "../../../common/form/domain/validationReport";
import Form from "../../../common/form";
import Input from "../../../common/input";
import Select from "../../../common/select";

export default function MovieForm({...props}) {

    /**
     * PROPS
     */
    const {id} = props.match.params;
    const existingMovie: Movie | undefined = id ? getMovie(id) : undefined;
    if (id && !existingMovie) props.history.replace("/not-found");

    /**
     * STATE
     */
    const [genres] = useState<Genre[]>(getGenres());
    const [movie, setMovie] = useState(existingMovie || undefined);
    const [report, setReport] = useState<ValidationReport>();
    const disableSubmit = useMemo(() => !movie || movie.validate().hasAnError(), [movie]);

    /**
     * BEHAVIOUR
     */
    const change = ({currentTarget: input}: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = input;

        const tempMovie: any = movie ? {...movie} : {};
        tempMovie[name] = name === "genre" ? getGenre(value) : value;

        const newMovie: Movie = new Movie(tempMovie);

        setReport(newMovie.validateOnly(input, report));
        setMovie(newMovie);
    }
    const save = () => {
        // Check
        if (!movie) return;
        // Save the movie :
        persist(movie);
        // Redirection :
        props.history.push("/movies");
    }

    /**
     * RENDERING
     */
    return (
        <React.Fragment>
            <h1>Movie Form</h1>
            <Form callback={save} disabled={disableSubmit} submitLabel="Save">
                <Input name="title" label="Title" value={movie && movie.title}
                       placeholder="Title" change={change}
                       error={report && report.getErrorMessage("title")}/>
                <Select items={genres} name="genre" label="Genre" value={movie && movie.genre && movie.genre._id}
                        change={change} error={report && report.getErrorMessage("genre")}/>
                <Input name="stock" label="Stock" value={movie && movie.stock}
                       type="number" placeholder="Number in stock" change={change}
                       error={report && report.getErrorMessage("stock")}/>
                <Input name="rate" label="Rate" value={movie && movie.rate}
                       type="number" placeholder="Daily rental rate" change={change}
                       error={report && report.getErrorMessage("rate")}/>
            </Form>
        </React.Fragment>
    );

}