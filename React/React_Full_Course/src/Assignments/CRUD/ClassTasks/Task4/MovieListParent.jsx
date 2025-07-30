import React from 'react';
import MovieListChild from './MovieListChild';

const MovieListParent = () => {
  const movies = [
    { id: 1, title: 'Movie 1', year: 2020 },
    { id: 2, title: 'Movie 2', year: 2021 },
    { id: 3, title: 'Movie 3', year: 2022 },
  ];

  return (
    <div>
      <h2>Movie List</h2>
      <MovieListChild movies={movies} />
    </div>
  );
};

export default MovieListParent;