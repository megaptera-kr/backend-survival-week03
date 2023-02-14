import { useForm } from 'react-hook-form';

import styled from 'styled-components';

import Comment from './Comment';

import useFetchComments from './hooks/useFetchComments';
import useComment from './hooks/useComment';

const Container = styled.div`
  & > form {
    display: flex;
    justify-content: space-between;
    margin-top: 10rem;
    padding: 1rem;
    border: 1px solid black;

    & > div {
      width: 80%;
    }

    & > div + div {
      width: 20%;
      text-align: end;
    }
  }

  label {
    margin-right: 1rem;
  }

  input {
    width: 80%;
    padding: .3rem;
  }

  button {
    padding: .3rem;
  }

  ul {
    padding: 0;
    list-style: none;
  }
`;

export default function Comments({ postId }) {
  const { comments, reload } = useFetchComments({ postId });

  const { saveComment, deleteComment } = useComment({ postId });

  const { setValue, register, handleSubmit, formState: { errors } } = useForm();

  const handleSaveComment = async (data) => {
    await saveComment(data);

    setValue('content', '');

    reload();
  };

  const handleDeleteComment = async (id) => {
    await deleteComment(id);

    reload();
  };

  if (!comments) {
    return (
      <p>
        Now loading...
      </p>
    );
  }

  return (
    <Container>
      <form onSubmit={handleSubmit(handleSaveComment)}>
        <div>
          <label htmlFor="input-content">
            댓글
          </label>
          <input
            id="input-content"
            {...register('content', { required: true })}
          />
          {errors.title && <span>필수 입력 값 입니다!</span>}
        </div>
        <div>
          <button type="submit">
            저장하기
          </button>
        </div>
      </form>
      {!comments.length ? (
        <p>댓글이 없습니다ㅜㅠ</p>
      ) : (
        <ul>
          {comments.map((comment) => (
            <li key={comment.id}>
              <Comment
                comment={comment}
                onClickEdit={handleSaveComment}
                onClickDelete={handleDeleteComment}
              />
            </li>
          ))}
        </ul>
      )}
    </Container>
  );
}
