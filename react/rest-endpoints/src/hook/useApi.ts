import {useEffect, useState} from "react";
import axios, {AxiosInstance, AxiosRequestConfig, AxiosResponse} from "axios";

const client: AxiosInstance = axios.create({
    baseURL: "https://jsonplaceholder.typicode.com/",
    timeout: 5000
});

export default function useApi(params: AxiosRequestConfig) {
    const [data, setData] = useState<any>();
    const [error, setError] = useState<string>();
    useEffect(() => {
        async function call() {
            try {
                const resp: AxiosResponse = await client.request(params);
                setData(resp.data);
            } catch (error: Error) {
                setError(error.toJSON().message);
            }
        }

        call();
    }, [params])
    return {data, error};
}