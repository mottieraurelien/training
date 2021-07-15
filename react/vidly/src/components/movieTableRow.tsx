import React from 'react';

export default function MovieTableRow({...props}) {

    /*
     * STATE
     */
    const {key, movie, remove} = props;

    /*
     * RENDERING
     */
    return (
        <tr key={key}>
            <td>{movie.title}</td>
            <td>{movie.genre.name}</td>
            <td>{movie.stock}</td>
            <td>{movie.rate}</td>
            <td>
                <button className="btn btn-danger btn-sm" onClick={() => remove(movie._id)}>Delete</button>
            </td>
        </tr>
    );

}