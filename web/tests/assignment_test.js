Feature('과제 테스트');

Scenario('게시판', ({ I }) => {
  I.amOnPage('/');

  I.click('글쓰기');

  I.waitForText('글 작성하기');

  I.fillField('제목', '백엔드 생존 코스');
  I.fillField('내용', '열심히 합시다.')

  I.click('저장하기');

  I.see('백엔드 생존 코스');

  I.click('백엔드 생존 코스');

  I.see('제목: 백엔드 생존 코스');
  I.see('열심히 합시다.');

  I.click('글 수정');

  I.fillField('제목', ' 데브로드 좋아요');
  I.fillField('내용', ' 파이팅!');

  I.click('저장하기');

  I.waitForText('데브로드 좋아요');

  I.waitForText('제목: 데브로드 좋아요')
  I.see('파이팅!');

  I.click('글 삭제');

  !I.see('게시물이 없습니다');
});


Scenario('댓글', ({ I }) => {
  I.amOnPage('/');

  I.click('글쓰기');

  I.waitForText('글 작성하기');

  I.fillField('제목', '데브로드');
  I.fillField('내용', '추천해주세요')

  I.click('저장하기');

  I.waitForText('데브로드');

  I.click('데브로드');

  I.fillField('댓글', '백엔드 생존 코스 들으세요!');
  I.click('저장하기');

  I.waitForText('백엔드 생존 코스 들으세요!');

  I.click('수정');

  I.waitForText('수정하기');

  I.fillField('댓글 수정', '백엔드 생존코스 추천해요');

  I.click('수정하기');

  I.see('백엔드 생존코스 추천해요');

  I.click('삭제');

  I.see('댓글이 없습니다');
});
