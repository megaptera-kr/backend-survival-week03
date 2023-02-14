import { fireEvent, render, screen } from '@testing-library/react';
import Viewer from './Viewer';

describe('Viewer', () => {
  const post = {
    title: '데브로드',
    content: '백엔드 개발자 생존코스',
  };

  const handleClickEdit = jest.fn();

  const handleClickDelete = jest.fn();

  function renderViewer() {
    render((
      <Viewer
        post={post}
        onClickEdit={handleClickEdit}
        onClickDelete={handleClickDelete}
      />
    ));
  }

  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('renders post', () => {
    renderViewer();

    screen.getByText(/데브로드/);
  });

  it('listens for edit button click event', () => {
    renderViewer();

    fireEvent.click(screen.getByText('글 수정'));

    expect(handleClickEdit).toBeCalled();
  });

  it('listens for delete button click event', () => {
    renderViewer();

    fireEvent.click(screen.getByText('글 삭제'));

    expect(handleClickDelete).toBeCalled();
  });
});
