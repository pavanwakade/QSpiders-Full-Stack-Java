import React from 'react';

const MovieListChild = ({ movies }) => {
  return (
    <ul>
      {movies.map((movie) => (
        <li key={movie.id}>
        {movie.id}  {movie.title} ({movie.year})
        </li>
      ))}
    </ul>
  );
};

export default MovieListChild;