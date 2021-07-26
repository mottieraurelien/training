import React, {Component} from "react";
import * as client from "./client";
import {toast, ToastContainer} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./App.css";

const resource = "/posts/";

class App extends Component {

    state = {
        posts: []
    };

    async componentDidMount() {
        const {data: posts, hasError, errorMessage} = await client.get(resource);
        if (hasError) {
            toast.error(errorMessage);
            this.setState({posts: []});
        } else {
            this.setState({posts});
        }
    }

    hideErrorMessage = () => {
        this.setState({...this.state.posts});
    }

    handleAdd = async () => {
        const originalPosts = this.state.posts;

        const inputPost = {title: "a", body: "b"};
        const posts = [inputPost, ...this.state.posts];
        this.setState({posts, errorMessage: ""});

        const {hasError, errorMessage} = await client.post(resource, inputPost);
        if (hasError) {
            toast.error(errorMessage);
            this.setState({posts: originalPosts});
        }
    };

    handleUpdate = async (oldPost) => {
        const originalPosts = this.state.posts;

        const newPost = {...oldPost};
        newPost.title = "UPDATED";

        const posts = originalPosts.map(originalPost => originalPost.id === oldPost.id ? newPost : {...originalPost});
        this.setState({posts});

        const {hasError, errorMessage} = await client.put(resource + newPost.id, newPost);
        if (hasError) {
            toast.error(errorMessage);
            this.setState({posts: originalPosts});
        }
    };

    handleDelete = async (oldPost) => {
        const originalPosts = this.state.posts;

        const posts = this.state.posts.filter(post => post.id !== oldPost.id);
        this.setState({posts});

        const {hasError, errorMessage} = await client.remove(resource + oldPost.id);
        if (hasError) {
            toast.error(errorMessage);
            this.setState({posts: originalPosts});
        }
    };

    render() {
        return (
            <React.Fragment>
                <ToastContainer/>
                <button className="btn btn-primary" onClick={this.handleAdd}>
                    Add
                </button>
                <table className="table">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.posts.map(post => (
                        <tr key={post.id + post.title}>
                            <td>{post.title}</td>
                            <td>
                                <button
                                    className="btn btn-info btn-sm"
                                    onClick={() => this.handleUpdate(post)}
                                >
                                    Update
                                </button>
                            </td>
                            <td>
                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() => this.handleDelete(post)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </React.Fragment>
        );
    }
}

export default App;
