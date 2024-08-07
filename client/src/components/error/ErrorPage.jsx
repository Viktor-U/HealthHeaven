import React from 'react';
import './error.css'; // Optional: for custom styling
import { Link } from 'react-router-dom';

const ErrorPage = () => {
  return (
    <div className='err-div'>
      <img src="\images\error-doctor.png" className='error-doctor' alt="doctor-err" />
      <div className="error-page">
        <h1>Oops!</h1>
        <p>Something went wrong.</p>
        <p>
          <Link to="/">Go back to the homepage</Link>
        </p>
      </div>
      <img src="\images\err-doctor.png" className='error-doctor err-doctor2' alt="doctor-err" />
    </div>

  );
};

export default ErrorPage;
