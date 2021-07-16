import React from 'react';
import Like from "../common/like";

export default function MovieTableRow({...props}) {

    /*
     * STATE
     */
    const {movie, remove, like} = props;

    /*
     * RENDERING
     */
    return (
        <tr>
            <td>{movie.title}</td>
            <td>{movie.genre.name}</td>
            <td>{movie.stock}</td>
            <td>{movie.rate}</td>
            <td>
                <Like id={movie._id} liked={movie.liked} like={like} />
            </td>
            <td>
                <button className="btn btn-danger btn-sm" onClick={() => remove(movie._id)}>Delete</button>
            </td>
        </tr>
    );

}