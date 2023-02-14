import { useState } from 'react';

import styled from 'styled-components';

import Posts from './Posts';

import useFetchPosts from './hooks/useFetchPosts';
import usePost from './hooks/usePost';
import Panel from './Panel';

const Title = styled.h1`
  display: block;
  text-align: center;
`;

const Menu = styled.div`
  text-align: end;
  
  button {
    font-size: 1.1rem;
    padding: .5em 1em;
    cursor: pointer;
  }
`;

const Wrapper = styled.div`
  display: flex;
  width: 100%;
  margin-top: 1em;
  border-top: 1px solid black;
`;

export default function App() {
  const [mode, setMode] = useState('');
  const [postId, setPostId] = useState('');

  const { posts, reload } = useFetchPosts();

  const { savePost, deletePost } = usePost();

  const handleClickPost = (id) => {
    setPostId(id);
    setMode('post');
  };

  const handleClickEdit = () => {
    setMode('edit');
  };

  const handleClickSave = async (data) => {
    await savePost(data);

    reload();

    if (!data.id) {
      setMode('');
    }
  };

  const handleClickDelete = async (id) => {
    await deletePost(id);

    reload();

    setMode('');
  };

  return (
    <>
      <Title>메가테라 게시판</Title>
      <Menu>
        <button
          type="button"
          onClick={handleClickEdit}
        >
          글쓰기
        </button>
      </Menu>
      <Wrapper>
        <Posts
          posts={posts}
          onClick={handleClickPost}
        />
        <Panel
          mode={mode}
          postId={postId}
          onClickSave={handleClickSave}
          onClickDelete={handleClickDelete}
        />
      </Wrapper>
    </>
  );
}
