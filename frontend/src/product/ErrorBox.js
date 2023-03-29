import React from 'react';
import Alert from 'react-bootstrap/Alert';

function ErrorBox(props) {
  if (props.error) {
    return (
      <Alert variant="danger">
        {props.error}
      </Alert>
    );
  } else {
    return null;
  }
}

export default ErrorBox