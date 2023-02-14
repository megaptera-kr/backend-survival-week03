import Editor from './Editor';
import Post from './Post';

export default function Panel({ mode, postId, onClickSave, onClickDelete }) {
  if(!mode) {
    return null;
  }

  if (mode === 'edit') {
    return (
      <Editor
        post={{}}
        onSubmit={onClickSave}
      />
    );
  }

  return (
    <Post
      postId={postId}
      onClickSave={onClickSave}
      onClickDelete={onClickDelete}
    />
  );

}
