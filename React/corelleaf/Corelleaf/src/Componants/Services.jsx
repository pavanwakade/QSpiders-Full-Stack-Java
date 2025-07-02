import React from 'react';

const Services = () => {
  const services = [
    {
      id: 1,
      title: "Web Development",
      description: "Building responsive, high-performance, and scalable web applications using modern frameworks.",
      icon: "🌐" // Globe emoji
    },
    {
      id: 2,
      title: "Mobile App Development",
      description: "Crafting intuitive and engaging mobile experiences for iOS and Android platforms.",
      icon: "📱" // Mobile phone emoji
    },
    {
      id: 3,
      title: "Cloud Solutions",
      description: "Designing and implementing robust cloud-based architectures for enhanced scalability and efficiency.",
      icon: "☁️" // Cloud emoji
    },
    {
      id: 4,
      title: "UI/UX Design",
      description: "Creating stunning and user-centric interfaces that provide exceptional user experiences.",
      icon: "🎨" // Palette emoji
    },
    {
      id: 5,
      title: "DevOps & Automation",
      description: "Streamlining development workflows with continuous integration, delivery, and deployment.",
      icon: "⚙️" // Gear emoji
    },
    {
      id: 6,
      title: "AI & Machine Learning",
      description: "Integrating intelligent solutions to automate processes, analyze data, and drive insights.",
      icon: "🧠" // Brain emoji
    },
    {
      id: 7,
      title: "Data Analytics",
      description: "Transforming raw data into actionable insights to support informed business decisions.",
      icon: "📊" // Bar chart emoji
    },
    {
      id: 8,
      title: "Cybersecurity Consulting",
      description: "Protecting your digital assets with comprehensive security strategies and implementation.",
      icon: "🔒" // Lock emoji
    }
  ];

  return (
    <div className="page bg-primary text-textLight p-8 my-8 mx-auto max-w-6xl rounded-lg shadow-lg animate-fadeInUp">
      <h1 className="text-accent mb-6 text-center">Our Services</h1>
      <p className="mb-8 text-center max-w-3xl mx-auto">
        At Corelleaf, we offer a comprehensive suite of software development services designed to meet your unique business needs. Our expertise spans across various technologies and domains, ensuring we deliver innovative and effective solutions.
      </p>
      
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 mt-8">
        {services.map(service => (
          <div key={service.id} className="bg-secondary rounded-lg shadow-md p-6 text-center transform transition-transform duration-300 hover:scale-105 border border-borderLight">
            <div className="text-5xl mb-4 text-accent">{service.icon}</div>
            <h2 className="text-xl font-semibold text-textLight mb-2">{service.title}</h2>
            <p className="text-gray-400">{service.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Services;
