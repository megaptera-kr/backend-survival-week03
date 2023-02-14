import { useState } from 'react';

import { useForm } from 'react-hook-form';

import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: .5rem;
  padding: 1rem;
  border: 1px solid black;
  
  form{
    display: flex;
    width: 100%;

    label {
      display: none;
    }
    
    input {
      width: 100%;
    }
  }

  div {
    width: 70%;
  }

  div + div {
    display: flex;
    justify-content: end;
    width: 30%;
  }

  button {
    margin-inline: .5rem;
  }
`;

export default function Comment({
  comment, onClickEdit, onClickDelete,
}) {
  const [mode, setMode] = useState('view');

  const { setValue, register, handleSubmit, formState: { errors } } = useForm();

  const handleClickEdit = () => {
    setValue('content', comment.content);
    setMode('edit');
  };

  const handleSubmitComment = (data) => {
    const { content } = data;
    onClickEdit({
      ...comment,
      content,
    });

    setMode('view');
  };

  if (mode === 'edit') {
    return (
      <Container>
        <form onSubmit={handleSubmit(handleSubmitComment)}>
          <div>
            <label htmlFor="input-edit">
              댓글 수정
            </label>
            <input
              id="input-edit"
              {...register('content', { required: true })}
            />
            {errors.title && <span>필수 입력 값 입니다!</span>}
          </div>
          <div>
            <button type="submit">
              수정하기
            </button>
          </div>
        </form>
      </Container>
    );
  }

  return (
    <Container>
      <div>{comment.content}</div>
      <div>
        <button
          type="button"
          onClick={handleClickEdit}
        >
          수정
        </button>
        <button
          type="button"
          onClick={() => onClickDelete(comment.id)}
        >
          삭제
        </button>
      </div>
    </Container>
  );
}
