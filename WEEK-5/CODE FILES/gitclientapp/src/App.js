import React, { Component } from "react";
import GitClient from "./GitClient";

class App extends Component {

    constructor(props) {
        super(props);

        this.state = {
            repositories: []
        };
    }

    componentDidMount() {
        GitClient.getRepositories("techiesyed")
            .then(data => {
                this.setState({
                    repositories: data
                });
            });
    }

    render() {
        return (
            <div>
                <h1>GitHub Repositories</h1>

                <ul>
                    {
                        this.state.repositories.map((repo, index) => (
                            <li key={index}>{repo}</li>
                        ))
                    }
                </ul>
            </div>
        );
    }
}

export default App;