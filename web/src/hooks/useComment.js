import axios from 'axios';

const BASE_URL = 'http://localhost:8080';

export default function useComment({ postId }) {
  const params = `postId=${postId}`;

  const saveComment = async (data) => {
    const { id } = data;
    if (id) {
      await axios.put(`${BASE_URL}/comments/${id}?${params}`, data);

      return;
    }

    await axios.post(`${BASE_URL}/comments?${params}`, data);
  }

  const deleteComment = async (id) => {
    await axios
      .delete(`${BASE_URL}/comments/${id}?${params}`);
  }

  return {
    saveComment,
    deleteComment,
  };
}
