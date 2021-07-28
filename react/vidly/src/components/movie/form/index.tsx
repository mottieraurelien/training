import React, {ChangeEvent, useEffect, useMemo, useState} from "react";
import {getMovie, saveMovie} from "../../../services/movieService";
import Movie from "../../../domain/movie";
import Genre from "../../../domain/genre";
import {getGenre, getGenres} from "../../../services/genreService";
import ValidationReport from "../../../common/form/domain/validationReport";
import Form from "../../../common/form";
import Input from "../../../common/input";
import Select from "../../../common/select";

export default function MovieForm({...props}) {

    /**
     * STATE
     */
    const [genres, setGenres] = useState<Genre[]>();
    const [movie, setMovie] = useState<Movie>();
    const [report, setReport] = useState<ValidationReport>();
    const disableSubmit = useMemo(() => !movie || movie.validate().hasAnError(), [movie]);

    /**
     * DATA LOADING
     */
    useEffect(() => {
        const fetch = async () => {
            const genres = await getGenres();
            if (genres) setGenres(genres);
        }
        if (!genres) fetch();
    }, [genres]);
    useEffect(() => {
        const {id} = props.match.params;
        const fetch = async () => {
            try {
                const existingMovie = await getMovie(id);
                setMovie(existingMovie);
            } catch {
                props.history.replace("/not-found");
            }
        }
        if (id && !movie) fetch();
    }, [movie, props]);

    /**
     * BEHAVIOUR
     */
    const change = ({currentTarget: input}: ChangeEvent<HTMLInputElement>) => {
        const {name, value} = input;

        const tempMovie: any = movie ? {...movie} : {};
        tempMovie[name] = name === "genre" ? getGenre(genres, value) : value;

        const newMovie: Movie = new Movie(tempMovie);

        setReport(newMovie.validateOnly(input, report));
        setMovie(newMovie);
    }
    const save = async () => {
        if (!movie) return;
        await saveMovie(movie);
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
                <Input name="numberInStock" label="Stock" value={movie && movie.numberInStock}
                       type="number" placeholder="Number in stock" change={change}
                       error={report && report.getErrorMessage("numberInStock")}/>
                <Input name="dailyRentalRate" label="Rate" value={movie && movie.dailyRentalRate}
                       type="number" placeholder="Daily rental rate" change={change}
                       error={report && report.getErrorMessage("dailyRentalRate")}/>
            </Form>
        </React.Fragment>
    );

}