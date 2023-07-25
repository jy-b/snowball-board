# snowball-board

회원가입, 로그인, 회원 정보 수정<br>
로그인 시 access token을 response에 담아서 발급<br>
request시 Header에 추가하여 서버에 요청<br>
refresh token은 httponly secure cookie에 저장하여 access token 재발급에 사용<br>
회원 가입 및 정보 수정 시 이메일, 닉네임 중복 확인<br>

[API명세서](https://www.notion.so/jongyoonb/3-API-DOCS-6680e9e353914410bdf3a0b4af8e8773)