/**
 * 회원가입 페이지('/member/signup')
 */
import modal from '../components/modal.js';

import changetitle from '../utils/changeTitle.js';

const memberSignup = () => {
  changetitle('회원가입');

  const html = `
    <p id="signup-title">회원가입</p>
    <div id="signup-box" class="input-box">
      <div><p>이메일</P><input type="text" id="userId" placeholder="이메일을 입력해 주세요" /></div>
      <div><p>비밀번호</p><input type="password" id="userPassword" placeholder="비밀번호를 입력해 주세요" /></div>
      <div><p>이름</p><input type="text" id="userName" placeholder="이름을 입력해 주세요" /></div>
      <div><p>생년월일</p><input id="userBirth" type="date"></div>
      <div><p>전화번호</p><input type="text" id="userPhone" placeholder="전화번호를 입력해 주세요" /></div>
      <div><p>성별</p><input type="text" id="userGender" placeholder="성별을 입력해 주세요" /></div>
      <br>
      <div class="signup-button-box">
        <input type="button" id="main-page-button" class="main-page-button" value="메인으로" />
        <input type="button" id="signup-button" value="회원가입하기" />
      </div>
    </div>
    ${modal()}
  `;

  return html;
};

export default memberSignup;
