import React from "react";
import ProfileCard from "./ProfileCard";
import HobbiesList from "./HobbiesList";
import FavoriteColor from "./FavoriteColor";
import Counter from "./Counter";
import ThemeToggle from "./ThemeToggle";
import FormStateTracker from "./FormStateTracker";
import ToDoList from "./ToDoList";
import ShoppingCart from "./ShoppingCart";
import AlertButton from "./AlertButton";
import ConsoleLogFormData from "./ConsoleLogFormData";
import LiveTextPreview from "./LiveTextPreview";
import ParagraphToggle from "./ParagraphToggle";
import DropdownDisplay from "./DropdownDisplay";
import FetchAndDisplayJoke from "./FetchAndDisplayJoke";
import LiveDigitalClock from "./LiveDigitalClock";
import PostLoginGreeting from "./PostLoginGreeting";
import WeatherStatusMessage from "./WeatherStatusMessage";
import LoadingIndicator from "./LoadingIndicator";
import EmptyToDoListMessage from "./EmptyToDoListMessage";
import DynamicThemeRenderer from "./DynamicThemeRenderer";
import ColorListDisplay from "./ColorListDisplay";
import StudentGradesTable from "./StudentGradesTable";
import ListWithDeletion from "./ListWithDeletion";
import BasicLoginForm from "./BasicLoginForm";
import FeedbackSubmissionForm from "./FeedbackSubmissionForm";
import EmailFormatValidator from "./EmailFormatValidator";
import ShoppingListEntryForm from "./ShoppingListEntryForm";
import SimpleContactUsForm from "./SimpleContactUsForm";
import Task1 from "./Task1/Task1.jsx";
import MovieListChild from "./MovieListChild.jsx";

const ClassTasks = () => {
  const hobbies = ["Gaming", "Travling", "Coding"];
  const tasks = ["Task 1", "Task 2", "Task 3"];
  const movies = [
    { id: 1, title: "Iron Man", year: 2008 },
    { id: 2, title: "The Fantastic Four", year: 2025 },
    { id: 3, title: "Ravan", year: 2004 },
  ];
  return (
    <div className="flex flex-col items-center justify-center h-screen bg-gray-100">
      {/* <p> 1. Welcome Message Component
 Design a reusable Welcome component that accepts a user’s name through props and displays a personalized greeting in the UI.
</p>
<br />
      <Task1 />  */}

      {/* <p>2.	User Profile Display
        Create a ProfileCard component that receives a user’s name, age, and location through props and displays them in a card-like layout.</p>
      <ProfileCard name="pavan" age={25} location="pune" /> */}

      {/* <p>3.	Hobbies List Renderer
        Build a component that takes an array of hobbies as props and renders them in an unordered list, with each hobby listed on a separate line.</p>
      <HobbiesList hobbies={hobbies} /> */}

      {/* <p>4. Favorite Color Component with Default Prop
        Implement a component that displays a user’s favorite color. If no color is passed via props, it should default to a predefined color.</p>
      <FavoriteColor color="blue" /> */}

      {/* 
      <p>5.	Movie List with Parent-Child Communication
        Develop a parent component that contains an array of movie objects and passes it to a child component to display each movie’s title and release year.</p>
       <MovieListChild movies={movies} /> */}

      {/* <p>🔷 useState Hook  </p>  <br /> */}

      {/* <p>6.	Counter with Controls
        Create a counter that displays a numeric value and has buttons to increase, decrease, and reset the count.</p>
      <Counter /> */}

      {/* 
      <p>7.	Theme Toggle Switch
        Build a toggle switch that changes the current mode of the application between “Light Mode” and “Dark Mode” when clicked.</p>
      <ThemeToggle /> */}

      {/* <p>8.	Real-Time Form State Tracker
        Design a form with multiple input fields (e.g., name, email). As the user types, reflect the updated values in real time somewhere on the screen.</p>
      <FormStateTracker /> */}

      {/* <p>9.	To-Do List with Add Functionality
        Develop a to-do list component where the user can add a new task via input. The list of tasks should update and display every newly added task.</p> */}
      {/* <ToDoList /> */}

      {/* <p>
        10.	Basic Shopping Cart Counter
        Implement a simple product item that increments the cart count whenever the “Add to Cart” button is clicked.
      </p>
      <ShoppingCart /> */}

      <h1>🔷 Event Handling</h1>
      {/* 
      <p>
        11. Alert Button Add a button that, when clicked, shows a browser alert
        with a predefined message. 12. Console Log Form Data on Submit
      </p>
      <AlertButton /> */}

      {/* <p>
        12. Console Log Form Data on Submit Create a basic form that captures
        user input. When submitted, the input values should be logged to the
        browser console.
      </p>
      <ConsoleLogFormData /> */}



      <h2>12. Live Text Preview</h2>
      <LiveTextPreview />
      
      {/* 

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
