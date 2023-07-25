# snowball-board

실행을 위하여 User와 관련없는 코드들은 모두 삭제된 상태입니다
View파일들은 기본적인 기능만 하는 상태 입니다.

추후에 게시판 기능이 완성되면 rebase 해서 develop으로 PR날리겠습니다.

참고용으로 사용해 주세요

# 기본 로직

로그인 시 access_token 발급, refresh_token은 httpOnly secure 쿠키에 저장<br>
서버에 request요청시 access_token을 requset Header에 담아서 전송<br>
request를 security filter에서 snip해서 검증 후 SecurityContextHolder에 인증 정보 저장, 컨트롤러로<br><br>
### SecurityContextHolder에 저장된 정보<br>
subject : userAccount(로그인 ID)<br>
Cliams<br>
userId(user_tb의 pk)<br>
userRole(회원 등급)<br><br>
Cliams의 userId를 이용해 join으로 유저 정보를 사용하시면 됩니다<br>
access_token의 만료 여부를 client에서 감지하여 서버로 access_token의 재발급 요청<br>
httpOnly secure Cookie의 refresh_token을 검증하여 access_token을 발급하여 response<br><br>
# 회원가입

email, nickname 중복 체크<br>

client에서 동적으로 중복 체크 하려면 endpoint 추가 필요(아이디)<br>

아래 토큰 발급은 회원가입시 자동 로그인을 위해 구현됨<br>

access_token -> response(LocalStorage에 담아서 Client에서 사용해 주세요)<br>
refresh_token ->httpOnlyCookie(js에서 접근 불가능)<br><br>

# 로그인

access_token -> response(LocalStorage에 담아서 Client에서 사용해 주세요)<br>
refresh_token ->httpOnlyCookie(js에서 접근 불가능)<br>

[API명세서](https://www.notion.so/jongyoonb/3-API-DOCS-6680e9e353914410bdf3a0b4af8e8773)
