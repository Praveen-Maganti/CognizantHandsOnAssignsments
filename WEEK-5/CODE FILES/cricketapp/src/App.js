import ListOfPlayers from "./ListOfPlayers";
import { IndianPlayers } from "./ListOfIndianPlayers";
import OddPlayers from "./OddPlayers";
import EvenPlayers from "./EvenPlayers";
import PlayersBelow70 from "./PlayersBelow70";
import IndianPlayerList from "./IndianPlayerList";

function App() {
  const players = [
  { name: "First Player", score: 95 },
  { name: "Second Player", score: 82 },
  { name: "Third Player", score: 76 },
  { name: "Fourth Player", score: 68 },
  { name: "Fifth Player", score: 60 },
  { name: "Sixth Player", score: 45 }
];
  var flag=true;
  if(flag){
    return(
      <div>
        <h1>List of Playes</h1>
        <ListOfPlayers players={players}/>
        <hr/>
        <h1>List of Players having Scores Less than 70</h1>
        <PlayersBelow70 players={players}/>
      </div>
    )
  }else{
    
    return(
      <div>
        <h1>Indian Team</h1>
          <h1>Odd Players</h1>
          <OddPlayers players={IndianPlayers}/>
          <hr/>

          <h1>Even Players</h1>
          <EvenPlayers players={IndianPlayers}/>
          <hr/>

        <h1>List of Indian Players Merged:</h1>
        <IndianPlayerList players={IndianPlayers}/>
      </div>
    )
  }
}

export default App;
