import axios from 'axios';

import useSWR from 'swr';

const BASE_URL = 'http://localhost:8080';

const fetcher = (path) => axios
  .get(`${BASE_URL}/${path}`)
  .then((response) => response.data);

export default function useFetchPosts() {
  const { data: posts, mutate } = useSWR('posts', fetcher);

  return {
    posts,
    reload:mutate,
  }
}
