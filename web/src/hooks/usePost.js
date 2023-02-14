import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

export default function usePost() {
  const savePost = async (data) => {
    const { id } = data;
    if (id) {
      await axios.put(`${BASE_URL}/posts/${id}`, data);

      return;
    }

    await axios.post(`${BASE_URL}/posts`, data);
  }

  const deletePost = async (id) => {
    await axios.delete(`${BASE_URL}/posts/${id}`);
  }

  return {
    savePost,
    deletePost,
  }
}
