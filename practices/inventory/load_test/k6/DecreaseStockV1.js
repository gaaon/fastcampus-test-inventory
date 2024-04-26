import http from 'k6/http';
import {sleep, check} from 'k6';

export const options = {
  vus: 1,
  duration: '15s',
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
