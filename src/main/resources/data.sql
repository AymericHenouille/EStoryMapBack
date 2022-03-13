-- Insert users
insert into users (name, email) values ('Aymeric', 'aymeric.henouille@gmail.com');
insert into user_sensitive (user_id, password) values (1, '$2y$10$RYj3qAkFcMbKgTseZFfpOeXX5LyxV3VYcPmO5nD7OeGmP1X0CYhbm');

---- Insert workspaces
--insert into workspaces (label, color, emoticon, owner_id) values ('Workspace 1', '#2ecc71', U&'\d83e\dd13', 1);
--insert into workspaces (label, color, emoticon, owner_id) values ('Workspace 2', '#e74c3c', U&'\d83e\dd14', 1);
--
---- share workspace to user
--insert into shared_workspaces (workspace_id, user_id) values (3, 1);
--insert into shared_workspaces (workspace_id, user_id) values (1, 1);
--
--insert into projects (label, color, emoticon) values ('Endor base', '#2980b9', U&'\d83e\dd17');
--insert into projects (label, color, emoticon) values ('Death star', '#2c3e50', U&'\d83e\dd18');
--
--insert into project_workspace (workspace_id, project_id) values (1, 1);
--insert into project_workspace (workspace_id, project_id) values (1, 2);