import { assertE2eDataEquals } from './assertions'

const existingItemId = request.environment.get('existingItemId')
const expectedStock = parseInt(request.variables.get('newStock'))

assertE2eDataEquals(existingItemId, expectedStock)