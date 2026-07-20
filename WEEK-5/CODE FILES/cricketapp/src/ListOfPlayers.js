
function ListOfPlayers({players}){
    return(
        <ul>
        {players.map((player)=>(
            <li key={player.name}>Mr. {player.name} <span> {player.score} </span></li>
        ))}
        </ul>
    )
}

export default ListOfPlayers;