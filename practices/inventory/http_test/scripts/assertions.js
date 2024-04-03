export function assertE2eErrorEquals(
    expectedStatusCode,
    expectedErrorCode,
    expectedErrorMessage
) {
    client.test(`응답의 status는 ${expectedStatusCode}이다`, function () {
        client.assert(response.status === expectedStatusCode, `응답의 status는 ${expectedStatusCode}가 아니다`)
    })

    client.test("응답의 body는 error를 포함한다", function () {
        const error = response.body.error
        client.assert(error !== null)
        client.assert(error.code === expectedErrorCode)
        client.assert(error.local_message === expectedErrorMessage)
    })
}

export function assertE2eDataEquals(expectedItemId) {
    client.test("응답의 status는 200이다", function () {
        client.assert(response.status === 200, '응답의 status는 200이 아니다')
    })

    client.test('응답의 body는 data를 포함한다', function () {
        const data = response.body.data
        client.assert(data !== null)
        client.assert(data.item_id === expectedItemId)
        client.assert(data.stock !== null && data.stock >= 0)
    })
}