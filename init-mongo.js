db = db.getSiblingDB('demo');

db.employee.insertMany([
  { name: "Alice", role: "Engineer", age: Math.floor(Math.random() * 30) + 20 },
  { name: "Bob", role: "Manager", age: Math.floor(Math.random() * 30) + 20 },
  { name: "Charlie", role: "Designer", age: Math.floor(Math.random() * 30) + 20 },
  { name: "David", role: "QA", age: Math.floor(Math.random() * 30) + 20 },
  { name: "Eve", role: "DevOps", age: Math.floor(Math.random() * 30) + 20 },
  { name: "Frank", role: "Support", age: Math.floor(Math.random() * 30) + 20 },
  { name: "Grace", role: "Engineer", age: Math.floor(Math.random() * 30) + 20 },
  { name: "Hank", role: "HR", age: Math.floor(Math.random() * 30) + 20 },
  { name: "Ivy", role: "Product Manager", age: Math.floor(Math.random() * 30) + 20 },
  { name: "Jake", role: "CTO", age: Math.floor(Math.random() * 30) + 20 }
]);