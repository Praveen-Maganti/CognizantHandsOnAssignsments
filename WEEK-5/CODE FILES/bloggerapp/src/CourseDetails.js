import './App.css'
function CourseDetails(props){
    const coursedet=props.coursedet;

    return(
        <div className="div">
        <h1>Course Details</h1>
        <ul>
            {coursedet.map((item,index)=>(
                <li key={index}>
                    <h2>{item.name}</h2>
                    <p>{item.date}</p>
                </li>
            ))}
        </ul>
        </div>
    );
}

export default CourseDetails;