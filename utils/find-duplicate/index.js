const fs = require('fs')

function main() {
    const result = fs.readFileSync('result.txt', 'utf8')

    const numbers = result.split('\n').map(Number)

    const duplicates = numbers.filter((number, index) =>
        numbers.indexOf(number) !== index
    )

    if (duplicates.length > 0) {
        throw new Error(`Duplicates found: ${duplicates.join(', ')}`)
    }
}

main()