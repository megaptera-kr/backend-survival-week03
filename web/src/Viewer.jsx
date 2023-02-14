import styled from 'styled-components';

import Comments from './Comments';

const Menu = styled.div`
  display: flex;
  justify-content: end;
  
  button {
    padding: .3rem .5rem;
    margin-right: 1rem;
  }
`;

export default function Viewer({ post, onClickEdit, onClickDelete }) {
  return (
    <>
      <Menu>
        <button
          type="button"
          onClick={onClickEdit}
        >
          글 수정
        </button>
        <button
          type="button"
          onClick={() => onClickDelete(post.id)}
        >
          글 삭제
        </button>
      </Menu>
      <h2>
        제목:
        {' '}
        {post.title}
      </h2>
      <div>
        <div>
          내용:
          {' '}
        </div>
        <p>
          {post.content}
        </p>
      </div>
      <Comments postId={post.id} />
    </>
  );
}
