import React from 'react';

const Portfolio = () => {
  const projects = [
    {
      id: 1,
      title: "E-commerce Platform",
      description: "Developed a scalable e-commerce platform with secure payment gateways and intuitive user experience.",
      image: "https://via.placeholder.com/400x250/a5d6a7/ffffff?text=E-commerce",
      link: "#"
    },
    {
      id: 2,
      title: "Mobile Banking App",
      description: "Designed and built a secure and feature-rich mobile banking application for iOS and Android.",
      image: "https://via.placeholder.com/400x250/81c784/ffffff?text=Mobile+Banking",
      link: "#"
    },
    {
      id: 3,
      title: "Healthcare Management System",
      description: "Created a comprehensive system for managing patient records, appointments, and billing for clinics.",
      image: "https://via.placeholder.com/400x250/66bb6a/ffffff?text=Healthcare",
      link: "#"
    },
    {
      id: 4,
      title: "AI-Powered Analytics Dashboard",
      description: "Developed an interactive dashboard leveraging AI for real-time data analysis and visualization.",
      image: "https://via.placeholder.com/400x250/4caf50/ffffff?text=AI+Analytics",
      link: "#"
    },
    {
      id: 5,
      title: "Social Media Integration Tool",
      description: "Built a tool to seamlessly integrate various social media platforms for unified content management.",
      image: "https://via.placeholder.com/400x250/43a047/ffffff?text=Social+Media",
      link: "#"
    },
    {
      id: 6,
      title: "Custom CRM Solution",
      description: "Tailored CRM system to manage customer relationships, sales pipelines, and marketing campaigns.",
      image: "https://via.placeholder.com/400x250/388e3c/ffffff?text=Custom+CRM",
      link: "#"
    }
  ];

  return (
    <div className="page bg-primary text-textLight p-8 my-8 mx-auto max-w-6xl rounded-lg shadow-lg animate-fadeInUp">
      <h1 className="text-accent mb-6 text-center">Our Portfolio</h1>
      <p className="mb-8 text-center max-w-3xl mx-auto">
        Explore some of our recent projects that showcase our expertise in delivering high-quality software solutions across various industries.
      </p>
      
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 mt-8">
        {projects.map(project => (
          <div key={project.id} className="bg-secondary rounded-lg shadow-md overflow-hidden transform transition-transform duration-300 hover:scale-105 border border-borderLight">
            <img src={project.image} alt={project.title} className="w-full h-48 object-cover" />
            <div className="p-6">
              <h2 className="text-xl font-semibold text-textLight mb-2">{project.title}</h2>
              <p className="text-gray-400 mb-4">{project.description}</p>
              <a href={project.link} className="text-accent hover:underline font-medium">View Project &rarr;</a>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Portfolio;
