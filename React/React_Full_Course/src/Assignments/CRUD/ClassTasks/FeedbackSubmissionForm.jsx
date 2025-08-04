import React, { useState } from "react";

const FeedbackSubmissionForm = () => {
  const [feedback, setFeedback] = useState("");
  const [submittedFeedback, setSubmittedFeedback] = useState("");

  const handleFeedbackChange = (event) => {
    setFeedback(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    setSubmittedFeedback(feedback);
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>Feedback:</label>
        <textarea value={feedback} onChange={handleFeedbackChange} />
        <br />
        <button type="submit">Submit</button>
      </form>
      {submittedFeedback && <p>Submitted Feedback: {submittedFeedback}</p>}
    </div>
  );
};

export default FeedbackSubmissionForm;
