import './App.css';
import React from 'react';

class App extends React.Component {
   constructor(){
      super();
      this.state={
        entryCount:0,
        exitCount:0
      }
   }
    updateEntry=()=>{
        this.setState((prev)=>({
          entryCount:prev.entryCount+1
        }))
    }

      updateExit=()=>{
        this.setState((prev)=>({
          exitCount:prev.exitCount+1
        }))
      }
      render(){
        const {exitCount,entryCount}=this.state;
        return (
          <div className="container">
              <div className="div">
                <button onClick={this.updateEntry}>Login</button>
                <p>{entryCount} People Entered!!!</p>
              </div>
              <div className="div">
                <button onClick={this.updateExit}>Exit</button>
                <p>{exitCount} People Left!!!</p>
              </div>
          </div>
        );
      }
}

export default App;
