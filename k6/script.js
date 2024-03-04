import http from 'k6/http';

export const options = {
  vus: 20,
  duration: '60s',
};

export default function() {
  // Make a request to the API
  const res = http.get('http://35.226.75.99:8080/greeting/world');

  // Check the status code
  if (res.status !== 200) {
    console.log(`Error: ${res.status} ${res.statusText}`);
  }

  // Check the response body
  if (res.body !== 'Hello, world!') {
    console.log(`Error: Unexpected response body: ${res.body}`);
  }
}
