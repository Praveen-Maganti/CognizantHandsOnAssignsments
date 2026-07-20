import axios from "axios";
import GitClient from "./GitClient";

jest.mock("axios");

describe("Git Client Tests", () => {

    test("should return repository names for techiesyed", async () => {

        const mockedData = [
            { name: "React-App" },
            { name: "SpringBoot-App" },
            { name: "NodeJS-App" }
        ];

        axios.get.mockResolvedValue({
            data: mockedData
        });

        const repositories = await GitClient.getRepositories("techiesyed");

        expect(repositories).toEqual([
            "React-App",
            "SpringBoot-App",
            "NodeJS-App"
        ]);

        expect(axios.get).toHaveBeenCalledWith(
            "https://api.github.com/users/techiesyed/repos"
        );
    });

});