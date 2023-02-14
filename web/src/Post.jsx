import { useEffect, useState } from 'react';

import _ from 'lodash';

import styled from 'styled-components';

import Editor from './Editor';
import Viewer from './Viewer';

import useFetchPost from './hooks/useFetchPost';

const Container = styled.div`
  width: 55%;
  padding: 1em;
  
  p {
    font-size: 1.1rem;
    padding-left: 1em;
  }
`;

export default function Post({
  postId, onClickSave, onClickDelete,
}) {
  const [isEdit, setEdit] = useState(false);

  const { post, reload } = useFetchPost({ postId });

  const handleClickEdit = () => {
    setEdit(true);
  };

  const handleClickSave = async (data) => {
    await onClickSave(data);

    setEdit(false);

    reload();
  };

  useEffect(() => {
    setEdit(false);
  }, [post]);

  if (_.isEmpty(post)) {
    return (
      <p>now loading...</p>
    );
  }

  if (isEdit) {
    return (
      <Editor
        post={post}
        onSubmit={handleClickSave}
      />
    );
  }

  return (
    <Container>
      <Viewer
        post={post}
        onClickEdit={handleClickEdit}
        onClickDelete={onClickDelete}
      />
    </Container>
  );
}
