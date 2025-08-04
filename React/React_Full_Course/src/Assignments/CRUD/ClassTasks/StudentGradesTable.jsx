import React from 'react';

const StudentGradesTable = () => {
  const students = [
    { id: 1, name: 'pavan', grade: 'A' },
    { id: 2, name: 'ketan', grade: 'B' },
    { id: 3, name: 'akash', grade: 'C' },
  ];

  return (
    <table>
      <thead>
        <tr>
          <th>id</th>
          <th>Name</th>
          <th>Grade</th>
        </tr>
      </thead>
      <tbody>
        {students.map((student) => (
          <tr key={student.id}>
            <td>{student.id}</td>
            <td>{student.name}</td>
            <td>{student.grade}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default StudentGradesTable;