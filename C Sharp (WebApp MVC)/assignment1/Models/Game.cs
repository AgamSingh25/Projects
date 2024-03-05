using System.ComponentModel.DataAnnotations;

namespace assignment1.Models
{
    public class Game
    {
        [Key]
        public int GameId { get; set; }

        [Required]
        public string Title { get; set; }

        [Required]
        public string Developer { get; set; }

        [Required]
        [GenereValidation(ErrorMessage = "Choose one of the three genre (Action, Simulation and Puzzle).")]
        public string Genre { get; set; }

        [Required]
        [Display(Name = "Release Year")]
        [ReleaseYearValidation(ErrorMessage = "The Release Year should be atleast 3 years back.")]
        public int ReleaseYear { get; set; }

        [DataType(DataType.Date)]
        [Display(Name = "Purchase Date")]
        [PurchaseDateValidation(ErrorMessage = "Date can't be in future.")]
        public DateTime? PurchaseDate { get; set; }

        [Range(1,100)]
        public int? Rating { get; set; }
    }
}
