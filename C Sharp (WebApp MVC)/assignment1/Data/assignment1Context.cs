using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using assignment1.Models;
using Humanizer.Localisation;
using Microsoft.CodeAnalysis.Elfie.Serialization;

namespace assignment1.Data
{
    public class assignment1Context : DbContext
    {
        public assignment1Context (DbContextOptions<assignment1Context> options)
            : base(options)
        {
            Database.EnsureCreated();
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Game>().HasData(
                new Game
                {
                    GameId = 1,
                    Title = "Call of Duty",
                    Developer = "Infinity Ward",
                    Genre = "Action",
                    ReleaseYear = 2003,
                    PurchaseDate = new DateTime(2022, 05, 07),
                    Rating = 6
                },

                new Game
                {
                    GameId = 2,
                    Title = "The Witcher 3: Wild Hunt",
                    Developer = "CD Projekt Red",
                    Genre = "Action",
                    ReleaseYear = 2015,
                    PurchaseDate = new DateTime(2022, 8, 15),
                    Rating = 9
                },

                new Game
                {
                    GameId = 3,
                    Title = "The Sims 4",
                    Developer = "Maxis",
                    Genre = "Simulation",
                    ReleaseYear = 2014,
                    PurchaseDate = new DateTime(2021, 11, 20),
                    Rating = 8
                },

                new Game
                {
                    GameId = 4,
                    Title = "Portal 2",
                    Developer = "Valve Corporation",
                    Genre = "Puzzle",
                    ReleaseYear = 2011,
                    PurchaseDate = new DateTime(2022, 02, 15)
                }
            );
        }

        public DbSet<assignment1.Models.Game> Game { get; set; } = default!;
    }
}
