
function PlayersBelow70({players}){
    return(
        <ul>
        {players
        .filter((player)=> player.score<=70)
        .map((player)=>(
            <li key={player.name}>Mr. {player.name} <span> {player.score} </span></li>
        ))}
        </ul>
    );
}

export default PlayersBelow70;