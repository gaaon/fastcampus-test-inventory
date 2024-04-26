import http from 'k6/http';
import {sleep, check} from 'k6';

const maxVus = 1500

// maxVus * 5 / 2 + maxVus * 10 + maxVus * 5 / 2 = 15 * maxVus
// 15 * maxVus 초 동안 실행
// 250ms delay이기 때문에
// 총 15 * maxVus * 4 = 60 * maxVus 번 실행
export const options = {
  scenarios: {
    decreaseStock: {
      executor: 'ramping-vus',
      startVUs: 0,
      stages: [
        {duration: '5s', target: maxVus},
        {duration: '10s', target: maxVus},
        {duration: '5s', target: 0},
      ],
    }
  }
};

const stockMap = {}

// https://grafana.com/docs/k6/latest/javascript-api/k6-http/response/
export default function () {
  const data = {
    quantity: 1
  }

  const resp = http.post('http://localhost:8080/api/v1/inventory/1/decrease', JSON.stringify(data), {
    headers: {
      'Content-Type': 'application/json',
    },
    timeout: '1s',
  })

  check(resp, {
    'is status 200': (r) => r.status === 200,
    'should have item_id 1': (r) => r.json('data.item_id') === '1',
    'should have stock gte 0': (r) => r.json('data.stock') >= 0,
  })

  if (stockMap[resp.json('data.stock')] ) {
    console.log('Duplicates found: ' + resp.json('data.stock'))
  } else {
    stockMap[resp.json('data.stock')] = true
  }

  sleep(0.25);
}
