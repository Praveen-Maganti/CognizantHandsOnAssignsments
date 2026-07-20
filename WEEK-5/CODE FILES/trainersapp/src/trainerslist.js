
import {Link} from 'react-router-dom';
import trainers from './trainersmock';

function TrainerList(){
    return(
        <div>
            <h2>Trainer Lists</h2>

            <ul>
                {trainers.map((trainer) => (
                    <li key={trainer.trainerId}>
                        <Link to={`/trainers/${trainer.trainerId}`}>
                            {trainer.name}
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    )
}

export default TrainerList;