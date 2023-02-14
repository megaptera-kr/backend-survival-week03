import axios from 'axios';

import useSWR from 'swr';

const BASE_URL = 'http://localhost:8080';

const fetcher = (url) => axios
  .get(`${BASE_URL}${url}`)
  .then((response) => response.data);

export default function useFetchPost({ postId }) {
  const { data: post, mutate } = useSWR(`/posts/${postId}`, fetcher);

  return {
    post,
    reload: mutate,
  }
}
