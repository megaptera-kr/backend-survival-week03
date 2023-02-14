import axios from 'axios';

import useSWR from 'swr';

const BASE_URL = 'http://localhost:8080';

const fetcher = (path) => axios
  .get(`${BASE_URL}/${path}`)
  .then((response) => response.data);

export default function useFetchComments({ postId }) {
  const params = `postId=${postId}`;

  const { data: comments, mutate } = useSWR(`comments?${params}`, fetcher);

  return {
    comments,
    reload: mutate,
  }
}
