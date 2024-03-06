import http from 'k6/http';

export const options = {
  vus: 20,
  duration: '60s',
};

export default function() {
  
//   const bearer = `${__ENV.TOKEN}`;
//   const headers = {
//     Authorization: `Bearer ${bearer}`,
//   };
//   const res = http.get(`${__ENV.SERVICE_URL}/greeting/world`, { headers: headers });
  
  const res = http.get(`${__ENV.SERVICE_URL}/greeting/world`);

  // Check the status code
  if (res.status !== 200) {
    console.log(`Error: ${res.status} ${res.statusText}`);
  }

  // Check the response body
  if (res.body !== 'Hello, world!') {
    console.log(`Error: Unexpected response body: ${res.body}`);
  }
}
