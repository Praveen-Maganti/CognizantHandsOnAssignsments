import BookDetails from './BookDetails';
import './App.css';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';

function App() {
  const bookdet=[
    {name:"Master React",price:670},
    {name:"Deep Dive into Angular 11",price:800},
    {name:"Mongo Essentails",price:450}
  ];

  const blogdet=[
    {name:"React Learning",author:"Stephen Biz",description:"Welcome to learning React!"},
    {name:"Installation",author:"Schewzdenier",description:"You can install React from npm"}
  ];

  const coursedet=[
    {name:"Angular",date:"4/5/2021"},
    {name:"React",date:"6/3/20201"}
  ];
  return (
    <div className="container">
      <div className="section">
        <CourseDetails coursedet={coursedet} />
      </div>

      <div className="section border">
        <BookDetails bookdet={bookdet} />
      </div>

      <div className="section border">
        <BlogDetails blogdet={blogdet} />
      </div>
    </div>
  );
}

export default App;
