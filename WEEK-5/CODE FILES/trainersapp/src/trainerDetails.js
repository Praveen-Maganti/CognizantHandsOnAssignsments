
import {useParams} from "react-router-dom";
import trainers from './trainersmock';

function TrainerDetails(){
    const {id}=useParams();

    const trainer=trainers.find(
        (t)=> t.trainerId===id
    );

    if(!trainer){
        return <h2>Trainer Not Found</h2>;
    }

    return(
        <div>
            <h2>Trainer Details</h2>
            <p><b>Id:</b> {trainer.trainerId}</p>
             <p><b>Name:</b> {trainer.name}</p>
            <p><b>Technology:</b> {trainer.technology}</p>
            <p><b>Email:</b> {trainer.email}</p>
            <p><b>Phone:</b> {trainer.phone}</p>
            <ul>
                {trainer.skills.map((skill, index) => (
                    <li key={index}>{skill}</li>
                ))}
            </ul>
        </div>
    )

}

export default TrainerDetails;