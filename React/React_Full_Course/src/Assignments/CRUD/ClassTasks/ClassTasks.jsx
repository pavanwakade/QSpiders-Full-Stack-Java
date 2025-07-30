import React from 'react';
import ProfileCard from './ProfileCard';
import HobbiesList from './HobbiesList';
import FavoriteColor from './FavoriteColor';
import MovieListParent from './Task4/MovieListParent';
import Counter from './Counter';
import ThemeToggle from './ThemeToggle';
import FormStateTracker from './FormStateTracker';
import ToDoList from './ToDoList';
import ShoppingCart from './ShoppingCart';
import AlertButton from './AlertButton';
import ConsoleLogFormData from './ConsoleLogFormData';
import LiveTextPreview from './LiveTextPreview';
import ParagraphToggle from './ParagraphToggle';
import DropdownDisplay from './DropdownDisplay';
import FetchAndDisplayJoke from './FetchAndDisplayJoke';
import LiveDigitalClock from './LiveDigitalClock';
import PostLoginGreeting from './PostLoginGreeting';
import WeatherStatusMessage from './WeatherStatusMessage';
import LoadingIndicator from './LoadingIndicator';
import EmptyToDoListMessage from './EmptyToDoListMessage';
import DynamicThemeRenderer from './DynamicThemeRenderer';
import ColorListDisplay from './ColorListDisplay';
import StudentGradesTable from './StudentGradesTable';
import ListWithDeletion from './ListWithDeletion';
import BasicLoginForm from './BasicLoginForm';
import FeedbackSubmissionForm from './FeedbackSubmissionForm';
import EmailFormatValidator from './EmailFormatValidator';
import ShoppingListEntryForm from './ShoppingListEntryForm';
import SimpleContactUsForm from './SimpleContactUsForm';
import Task1 from './Task1/Task1.jsx';

const ClassTasks = () => {
  const hobbies = ['Gaming', 'Travling', 'Coding'];
  const tasks = ['Task 1', 'Task 2', 'Task 3'];

  return (
    <div className='flex flex-col items-center justify-center h-screen bg-gray-100'>


      {/* <p> 1. Welcome Message Component
 Design a reusable Welcome component that accepts a user’s name through props and displays a personalized greeting in the UI.
</p>
<br />
      <Task1 />  */}

      {/* <p>2.	User Profile Display
        Create a ProfileCard component that receives a user’s name, age, and location through props and displays them in a card-like layout.</p>
      <ProfileCard name="pavan" age={25} location="pune" /> */}

      {/* <h2>3. Hobbies List Renderer</h2>
      <HobbiesList hobbies={hobbies} /> */}


      {/* <h2>4. Favorite Color Component with Default Prop</h2>
      <FavoriteColor color="blue" /> */}
      {/* 
      <h2>4. Movie List with Parent-Child Communication</h2>
      <MovieListParent />

      <h2>5. Counter with Controls</h2>
      <Counter />

      <h2>6. Theme Toggle Switch</h2>
      <ThemeToggle />

      <h2>7. Real-Time Form State Tracker</h2>
      <FormStateTracker />

      <h2>8. To-Do List with Add Functionality</h2>
      <ToDoList />

      <h2>9. Basic Shopping Cart Counter</h2>
      <ShoppingCart />

      <h2>10. Alert Button</h2>
      <AlertButton />

      <h2>11. Console Log Form Data on Submit</h2>
      <ConsoleLogFormData />

      <h2>12. Live Text Preview</h2>
      <LiveTextPreview />

      <h2>13. Paragraph Toggle Visibility</h2>
      <ParagraphToggle />

      <h2>14. Dropdown Menu Selection Display</h2>
      <DropdownDisplay />

      <h2>15. Fetch and Display Joke on Load</h2>
      <FetchAndDisplayJoke />

      <h2>16. Live Digital Clock</h2>
      <LiveDigitalClock />

      <h2>20. Post-Login Greeting</h2>
      <PostLoginGreeting />

      <h2>21. Weather Status Message</h2>
      <WeatherStatusMessage />

      <h2>22. Loading Indicator</h2>
      <LoadingIndicator />

      <h2>23. Empty To-Do List Message</h2>
      <EmptyToDoListMessage tasks={tasks} />

      <h2>24. Dynamic Theme Renderer</h2>
      <DynamicThemeRenderer />

      <h2>25. Color List Display</h2>
      <ColorListDisplay />

      <h2>26. Student Grades Table</h2>
      <StudentGradesTable />

      <h2>27. List with Deletion Functionality</h2>
      <ListWithDeletion />

      <h2>30. Basic Login Form</h2>
      <BasicLoginForm />

      <h2>31. Feedback Submission Form</h2>
      <FeedbackSubmissionForm />

      <h2>32. Email Format Validator</h2>
      <EmailFormatValidator />

      <h2>33. Shopping List Entry Form</h2>
      <ShoppingListEntryForm />

      <h2>34. Simple Contact Us Form</h2>
      <SimpleContactUsForm /> */}
    </div>
  );
};

export default ClassTasks;