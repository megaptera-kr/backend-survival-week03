import { useForm } from 'react-hook-form';

import _ from 'lodash';

import styled from 'styled-components';

const Container = styled.div`
  padding: 1em;
  width: 55%;
  
  label {
    font-size: 1.1rem;
    display: block;
    padding-block: 1rem .5rem;
  }
  
  input {
    font-size: 1.1rem;
    display: block;
    width: 100%;
    padding: .5rem;
  }
  
  textarea {
    font-size: 1.1rem;
    display: block;
    width: 100%;
    padding: .5rem;
  }
  
  span {
    color: red;
  }
  
  div + div+ div {
    text-align: end;
  }

  button {
    font-size: 1.1rem;
    margin-top: 1rem;
    padding: .5rem 1rem;
  }
`;

export default function Editor({ post, onSubmit }) {
  const { register, handleSubmit, formState: { errors } } = useForm();

  const handleSubmitPost = (data) => {
    if (!_.isEmpty(post)) {
      onSubmit({
        id: post.id,
        ...data,
      });
      return;
    }

    onSubmit(data);
  };

  return (
    <Container>
      <h2>글 작성하기</h2>
      <form onSubmit={(handleSubmit(handleSubmitPost))}>
        <div>
          <label htmlFor="input-title">
            제목
          </label>
          <input
            id="input-title"
            defaultValue={_.isEmpty(post) ? '' : post.title}
            {...register('title', { required: true })}
          />
          {errors.title && <span>필수 입력 값 입니다!</span>}
        </div>
        <div>
          <label htmlFor="input-content">
            내용
          </label>
          <textarea
            id="input-content"
            defaultValue={_.isEmpty(post) ? '' : post.content}
            rows={10}
            {...register('content', { required: true })}
          />
          {errors.content && <span>필수 입력 값 입니다!</span>}
        </div>
        <div>
          <button type="submit">
            저장하기
          </button>
        </div>
      </form>
    </Container>
  );
}
