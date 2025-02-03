
        // Function to add an achievement field
function addAchievement() {
    const achievementsList = document.getElementById('achievementsList');
    const input = document.createElement('input');
    input.type = 'text';
    input.className = 'achievement';
    input.placeholder = 'Achievement';
    achievementsList.appendChild(input);
}

// Function to add a description point inside a skill or project
function addSkillPoint(button, type) {
    const pointsContainer = button.previousElementSibling;
    const input = document.createElement('input');
    input.type = 'text';
    input.placeholder = 'Description point';
    input.className = `${type}-description-point`;
    pointsContainer.appendChild(input);
}

// Function to add a new skill entry
function addSkillEntry(type) {
    const container = document.getElementById(`${type}SkillsList`);
    const skillEntry = document.createElement('div');
    skillEntry.className = 'skill-entry';
    skillEntry.innerHTML = `
        <input type="text" class="skill-title ${type}-skill" placeholder="Skill Title">
        <div class="skill-description">
            <div class="bullet-points">
                <input type="text" placeholder="Description point" class="${type}-description-point">
            </div>
            <button type="button" onclick="addSkillPoint(this, '${type}')">Add Description Point</button>
        </div>
    `;
    container.appendChild(skillEntry);
}

// Function to add a project
function addProject() {
    const projectsList = document.getElementById('projectsList');
    const projectContainer = document.createElement('div');
    projectContainer.className = 'project-container';
    projectContainer.innerHTML = `
        <input type="text" placeholder="Project Title" class="project-title">
        <input type="text" placeholder="Technologies Used" class="project-tech">
        <div class="project-description">
            <div class="bullet-points">
                <input type="text" placeholder="Description point" class="description-point">
            </div>
            <button type="button" onclick="addSkillPoint(this, 'description')">Add Description Point</button>
        </div>
    `;
    projectsList.appendChild(projectContainer);
}

// Function to add an education field
function addEducationField() {
    const educationFields = document.getElementById('educationFields');
    const newFields = document.createElement('div');
    newFields.innerHTML = `
        <input type="text" placeholder="Degree" class="degree">
        <input type="text" placeholder="Institution" class="institution">
        <input type="text" placeholder="Score" class="score">
    `;
    educationFields.appendChild(newFields);
}
function generateResume() {
    const resume = document.getElementById('resume');
    const formData = {
        fullName: document.getElementById('fullName').value.trim(),
        email: document.getElementById('email').value.trim(),
        phone: document.getElementById('phone').value.trim(),
        github: document.getElementById('github').value.trim(),
        objective: document.getElementById('objective').value.trim()
    };

    let contactInfoHtml = '';
    if (formData.phone) contactInfoHtml += `<li>${formData.phone}</li>`;
    if (formData.email) contactInfoHtml += `<li>${formData.email}</li>`;
    if (formData.github) contactInfoHtml += `<li>${formData.github}</li>`;

    if (contactInfoHtml) {
        contactInfoHtml = `<ul class="bullet-list">${contactInfoHtml}</ul>`;
    }

    // Gather achievements
    let achievementsHtml = '';
    let achievements = document.querySelectorAll('.achievement');
    let achievementItems = [];
    achievements.forEach(achievement => {
        if (achievement.value.trim()) {
            achievementItems.push(`<li>${achievement.value.trim()}</li>`);
        }
    });
    if (achievementItems.length > 0) {
        achievementsHtml = `<div class="section"><h2>Achievements</h2><ul class="bullet-list">${achievementItems.join('')}</ul></div><hr>`;
    }

    // Gather skills
    let skillsHtml = '';
    const skillTypes = { 'java': 'Programming Languages', 'sql': 'Database & Query Languages', 'testing': 'Testing' };
    for (const [type, title] of Object.entries(skillTypes)) {
        let typeHtml = '';
        document.querySelectorAll(`.${type}-skill`).forEach(skill => {
            const skillTitle = skill.value.trim();
            let pointsHtml = '';
            skill.closest('.skill-entry').querySelectorAll(`.${type}-description-point`).forEach(point => {
                if (point.value.trim()) pointsHtml += `<li>${point.value.trim()}</li>`;
            });
            if (skillTitle && pointsHtml) {
                typeHtml += `<div class="skill-section"><div class="skill-title">• ${skillTitle}</div><ul class="bullet-list">${pointsHtml}</ul></div>`;
            }
        });
        if (typeHtml) skillsHtml += `<h3>${title}</h3>${typeHtml}`;
    }
    if (skillsHtml) skillsHtml = `<div class="section"><h2>Technical Skills</h2>${skillsHtml}</div><hr>`;

    // Gather projects
    let projectsHtml = '';
    document.querySelectorAll('.project-container').forEach(project => {
        const title = project.querySelector('.project-title').value.trim();
        const tech = project.querySelector('.project-tech').value.trim();
        let descriptionHtml = '';
        project.querySelectorAll('.description-point').forEach(point => {
            if (point.value.trim()) descriptionHtml += `<li>${point.value.trim()}</li>`;
        });

        if (title && descriptionHtml) {
            projectsHtml += `<div class="project-section">
                                <div class="project-title">${title}</div>
                                ${tech ? `<div>Technologies Used: ${tech}</div>` : ''}
                                <div>Description:</div>
                                <ul class="bullet-list">${descriptionHtml}</ul>
                            </div>`;
        }
    });
    if (projectsHtml) projectsHtml = `<div class="section"><h2>Projects</h2>${projectsHtml}</div><hr>`;

    // Gather education
    let educationHtml = '';
    let educationRows = [];
    document.querySelectorAll('.degree').forEach((degree, i) => {
        const degreeValue = degree.value.trim();
        const institution = document.querySelectorAll('.institution')[i].value.trim();
        const score = document.querySelectorAll('.score')[i].value.trim();
        if (degreeValue || institution || score) {
            educationRows.push(`<tr><td>${degreeValue}</td><td>${institution}</td><td>${score}</td></tr>`);
        }
    });
    if (educationRows.length > 0) {
        educationHtml = `<div class="section"><h2>Education</h2>
                            <table class="education-table">
                                <tr><th>Degree</th><th>Institution</th><th>Score</th></tr>
                                ${educationRows.join('')}
                            </table>
                        </div><hr>`;
    }

    // Generate final resume
    resume.innerHTML = `
        ${contactInfoHtml ? `<div class="contact-info">${contactInfoHtml}</div>` : ''}
        <h1>${formData.fullName}</h1>
        ${formData.objective ? `<div class="section"><h2>Objective</h2><p>${formData.objective}</p></div><hr>` : ''}
        ${achievementsHtml}
        ${skillsHtml}
        ${projectsHtml}
        ${educationHtml}
    `;
}

function downloadPDF() {
    const element = document.getElementById('resume');
    const fullName = document.getElementById('fullName').value.trim();
    const fileName = fullName ? fullName.replace(/\s+/g, '_') + '.pdf' : 'Resume.pdf';

    const opt = {
        margin: 10, // Margin in mm for proper spacing
        filename: fileName,
        image: { type: 'jpeg', quality: 1.0 }, // Maximum image quality
        html2canvas: { 
            scale: 4, // High-resolution rendering
            useCORS: true, // Allow cross-origin images
            logging: true, // Enable logging for debugging
            letterRendering: true, // Improve text rendering
        },
        jsPDF: { 
            unit: 'mm', 
            format: 'a4', // A4 size
            orientation: 'portrait', // Portrait orientation
        },
    };

    // Generate and save the PDF
    html2pdf()
        .set(opt)
        .from(element)
        .save();
}

  