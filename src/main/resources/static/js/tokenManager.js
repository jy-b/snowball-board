function isValidAccessToken(accessToken) {
    if (!accessToken) {
        throw new Error("생성된 토큰이 없어요"); // Throw an error for missing access token
    }

    const tokenParts = accessToken.split('.');
    if (tokenParts.length !== 3) {
        throw new Error("토큰 규격이 엉망이에요"); // Throw an error for invalid token format
    }

    // Split the access token into its three parts: header, payload, and signature
    const header = tokenParts[0];
    const payload = tokenParts[1];
    const signature = tokenParts[2];

    const decodedPayload = JSON.parse(atob(payload));
    const expirationDate = new Date(decodedPayload.exp * 1000);
    const now = new Date();
    if (now >= expirationDate) {
        alert("토큰이 만료됬어요. 재발급 해볼게용")
        return refreshAccessToken();
    }

    // token is valid
    return accessToken;
}

function refreshAccessToken() {
    // request to server /refresh-token
    fetch('/api/auth/refresh-token', {
        method: 'POST'
    })
        .then(response => {
                if (response.ok) {
                    alert("재발급 성공")
                    console.log("Refresh Access Token Success!")
                    return response.access_token;
                } else {
                    alert("재발급 실패")
                    console.log("Refresh Access Token Failed!")
                    window.href('/login')
                }
            }
        );
}

function AccessToken() {
    isValidAccessToken(localStorage.getItem('access_token')).then(response => {
        if (response.ok) {
            if (!localStorage.getItem('access_token') === response.access_token) {
                localStorage.setItem('access_token', response.access_token);
            }
        }
    }).catch(error => {
        console.error('refresh token is invalid', error);
        window.href('/login')
    });
}
