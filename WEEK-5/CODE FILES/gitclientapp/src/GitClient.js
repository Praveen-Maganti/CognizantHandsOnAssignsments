
import axios from "axios";

class GitClient {

    getRepositories(username) {
        return axios.get(`https://api.github.com/users/${username}/repos`)
            .then(response => {
                return response.data.map(repo => repo.name);
            });
    }

}

export default new GitClient();